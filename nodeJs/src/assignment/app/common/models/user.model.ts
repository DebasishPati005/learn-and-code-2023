import mongoose, { Schema } from 'mongoose';
import { UserRequest, UserRole } from '../types';

const userSchema = new Schema<UserRequest>(
    {
        name: { type: String, required: true },
        email: { type: String, required: true },
        role: { type: String, enum: UserRole, default: UserRole.viewer },
        password: { type: String, required: true },
        directoryLocation: { type: String, required: true },
    },
    { timestamps: true }
);

const User = mongoose.model<UserRequest>('User', userSchema);

export default User;
