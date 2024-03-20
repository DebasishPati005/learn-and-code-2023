import { UserRequest, UserResponse } from 'assignment/app/common/types';

export interface IUserService {
    saveNewUser(userReq: UserRequest): Promise<UserResponse>;
    findUser(email: string): Promise<UserResponse | null>;
}
