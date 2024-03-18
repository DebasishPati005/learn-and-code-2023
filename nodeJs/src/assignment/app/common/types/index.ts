import { Types } from 'mongoose';

export class StatusError extends Error {
    status: number | undefined;
}

export interface UserRequest {
    name: string;
    email: string;
    password: string;
    role: UserRole;
    directoryLocation?: string;
}

export enum UserRole {
    viewer = 'viewer',
    user = 'user',
    admin = 'admin',
}

export interface UserResponse extends Omit<UserRequest, 'password'> {
    id: Types.ObjectId;
}
