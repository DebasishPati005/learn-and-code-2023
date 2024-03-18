import { NextFunction, Request, Response } from 'express';
import User from '../../common/models/user.model';
import { UserRequest, StatusError } from '../../common/types';
import { ERROR_MESSAGE } from '../../common/constants';

import AuthService from './auth.service';

export default class AuthController {
    constructor(private authService: AuthService) {
        console.log({ authSer: this });
    }

    async getUsers(req: Request, res: Response, _next: NextFunction) {
        const userReq: { email: string } = req.body;
        const user = await User.findOne({ email: userReq.email });
        if (!user) {
            const err = new StatusError(ERROR_MESSAGE.noRecordFound);
            err.status = 422;
            return _next(err);
        }

        res.json(user);
    }

    async createUser(req: Request, res: Response, next: NextFunction) {
        console.log({ authSer: this.authService });
        try {
            const userReq: UserRequest = req.body;

            const newUser = await this.authService.createUser(userReq);
            res.status(201).json(newUser);
        } catch (error) {
            next(error);
        }
    }
}
