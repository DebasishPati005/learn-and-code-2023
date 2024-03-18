import express, { Application, Request, Response, NextFunction } from 'express';
import bodyParser from 'body-parser';
import authRouter from './routers/auth.router';
import { StatusError } from './common/types';

const app: Application = express();

app.use(bodyParser.json());

app.use('/auth', authRouter);
// app.use("/user",isAuth,userRouter);

app.use((error: StatusError, req: Request, res: Response, _next: NextFunction) => {
    const status = error.status || 500;
    return res.status(status).json({
        message: error.message,
    });
});

export default app;
