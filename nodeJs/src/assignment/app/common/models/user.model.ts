import mongoose, {  Schema } from 'mongoose';
import { UserResponse, UserRole } from '../types';

const userSchema = new Schema<UserResponse>(
    {
        name: { type: String, required: true },
        email: { type: String, required: true },
        role: { type: String, enum: UserRole, default: UserRole.viewer },
        password: { type: String, required: true },
        directoryLocation: { type: String, required: true },
    },
    { timestamps: true }
);

userSchema.set('toJSON', {
    transform: function (_doc, ret) {
        ret.id = ret._id;
        delete ret.password;
        delete ret.__v;
    },
});

const User = mongoose.model<UserResponse>('User', userSchema);

export default User;
