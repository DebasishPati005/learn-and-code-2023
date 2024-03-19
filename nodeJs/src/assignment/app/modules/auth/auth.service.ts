import { UserRequest, StatusError, UserResponse } from '../../common/types';
import { CONSTANTS, ERROR_MESSAGE, RESPONSE_MESSAGE, MailTemplates } from '../../common/constants';
import bcrypt from 'bcryptjs';
import DirectoryService from '../directory/directory.service';
import { IAuthService } from './auth.service.contract';
import { NotificationService } from '../notification/notification.service';
import UserService from '../user/user.service';

class AuthService implements IAuthService {
    private static authServiceInstance: AuthService;

    private constructor() {}

    public static getAuthServiceInstance(): AuthService {
        if (!AuthService.authServiceInstance) {
            AuthService.authServiceInstance = new AuthService();
        }
        return AuthService.authServiceInstance;
    }

    public async createUser(userReq: UserRequest): Promise<UserResponse> {
        const existingUser = await UserService.getUserServiceInstance().findUser(userReq.email);
        if (existingUser) {
            const err = new StatusError(ERROR_MESSAGE.userExists);
            err.status = 422;
            throw err;
        }

        const hashedPw = await bcrypt.hash(userReq.password, CONSTANTS.PASSWORD_SALT);
        const directoryLocation = await DirectoryService.getDirectoryServiceInstance().createDirectory(userReq);

        const newUser = await UserService.getUserServiceInstance().saveNewUser({ ...userReq, password: hashedPw, directoryLocation });
        await AuthService.getAuthServiceInstance().sendSignUpEmail(newUser);
        return newUser;
    }

    private async sendSignUpEmail(newUser: UserResponse): Promise<void> {
        const notificationService = NotificationService.getNotificationServiceInstance();
        await notificationService.sendMail({
            html: MailTemplates.getSignUpTemplate(newUser.name),
            subject: RESPONSE_MESSAGE.newAccountEmailSubject,
            to: newUser.email,
        });
    }
}

export default AuthService;
