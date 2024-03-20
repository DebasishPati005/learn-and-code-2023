import User from '../../common/models/user.model';
import { UserRequest, UserResponse } from '../../common/types';
import { IUserService } from './user.service.contract';

class UserService implements IUserService {
    private static userServiceInstance: UserService;

    private constructor() {}

    public static getUserServiceInstance(): UserService {
        if (!UserService.userServiceInstance) {
            UserService.userServiceInstance = new UserService();
        }
        return UserService.userServiceInstance;
    }

    public async saveNewUser(userReq: UserRequest): Promise<UserResponse> {
        const newUser = new User(userReq);
        const userResponse = await newUser.save();
        return userResponse;
    }

    public async findUser(email: string): Promise<UserResponse | null> {
        const user = await User.findOne({ email });
        return user;
    }
}

export default UserService;
