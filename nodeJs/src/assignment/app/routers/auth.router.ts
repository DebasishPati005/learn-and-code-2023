import { Router } from 'express';
import AuthController from '../modules/auth/auth.controller';

const router: Router = Router();
const authController = new AuthController();

router.post('/sign-up', authController.createUser);

export default router;
