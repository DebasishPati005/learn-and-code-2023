import User from '../../common/models/user.model';
import { UserRequest, StatusError } from '../../common/types';
import { CONSTANTS, ERROR_MESSAGE } from '../../common/constants';
import bcrypt from 'bcryptjs';
import DirectoryService from '../directory/directory.service';

export default class AuthService {
    async createUser(userReq: UserRequest) {
        const existingUser = await User.findOne({ email: userReq.email });
        if (!existingUser) {
            const err = new StatusError(ERROR_MESSAGE.noRecordFound);
            err.status = 422;
            throw err;
        }

        const hashedPw = await bcrypt.hash(userReq.password, CONSTANTS.PASSWORD_SALT);
        const directoryLocation = await DirectoryService.createDirectory(userReq);

        const newUser = await User.create({ ...userReq, password: hashedPw, directoryLocation });
        return newUser;
    }
}
