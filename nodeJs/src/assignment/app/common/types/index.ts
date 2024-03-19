import { Types, Document } from 'mongoose';

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

export interface UserResponse extends UserRequest, Document {
    id: Types.ObjectId;
}
