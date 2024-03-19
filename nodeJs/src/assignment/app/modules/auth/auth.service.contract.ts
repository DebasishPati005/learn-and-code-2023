import { UserRequest, UserResponse } from 'assignment/app/common/types';

export interface IAuthService {
    createUser(userReq: UserRequest): Promise<UserResponse>;
}
