import { NextFunction, Request, Response } from 'express';
import User from '../../common/models/user.model';
import { UserRequest, StatusError } from '../../common/types';
import { ERROR_MESSAGE } from '../../common/constants';

import AuthService from './auth.service';

export default class AuthController {
    constructor() {}

    async getUsers(req: Request, res: Response, next: NextFunction) {
        try {
            const userReq: { email: string } = req.body;
            const user = await User.findOne({ email: userReq.email });
            if (!user) {
                const err = new StatusError(ERROR_MESSAGE.noRecordFound);
                err.status = 422;
                throw err;
            }

            res.json(user);
        } catch (error) {
            next(error);
        }
    }

    async createUser(req: Request, res: Response, next: NextFunction) {
        try {
            const authService: AuthService = AuthService.getAuthServiceInstance();
            const userReq: UserRequest = req.body;
            const newUser = await authService.createUser(userReq);
            res.status(201).json(newUser);
        } catch (error) {
            next(error);
        }
    }
}
