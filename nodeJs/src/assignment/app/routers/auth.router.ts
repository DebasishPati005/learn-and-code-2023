import { Router } from 'express';
import AuthController from '../modules/authentication/authentication.controller';
import AuthService from '../modules/authentication/auth.service';

const router: Router = Router();
const authService: AuthService = new AuthService();
const authController = new AuthController(authService);
console.log({ authSer: authService });

router.post('/sign-up', authController.createUser);

router.post('/', (req, res, _next) => {
    res.json({
        send: 'hi',
    });
});

export default router;
