import { Transporter, createTransport } from 'nodemailer';
import { MailConfig, MailResponse } from '../../common/types/mail';
import { RESPONSE_MESSAGE } from '../../common/constants';

export class NotificationService {
    transporter!: Transporter;
    private static instance?: NotificationService;

    private constructor() {
        this.createConnection();
    }

    static getNotificationServiceInstance(): NotificationService {
        if (!this.instance) {
            return new NotificationService();
        } else {
            return this.instance;
        }
    }

    private createConnection(): void {
        this.transporter = createTransport({
            host: process.env.GOOGLE_SES_HOST,
            port: +process.env.GOOGLE_SES_PORT!,
            secure: process.env.GOOGLE_SES_SECURE?.toLowerCase() === 'true',
            service: process.env.GOOGLE_SES_SERVICE!,
            auth: {
                user: process.env.GOOGLE_SES_USER!,
                pass: process.env.GOOGLE_SES_PASSWORD!,
            },
        });
    }

    public async sentMail(mailConfig: MailConfig): Promise<MailResponse> {
        try {
            await this.transporter.sendMail({
                from: process.env.GOOGLE_SES_USER,
                ...mailConfig,
            });

            return {
                message: RESPONSE_MESSAGE.mailSendSuccessMessage,
                sentMail: true,
            };
        } catch {
            return { message: RESPONSE_MESSAGE.mailSendSuccessMessage, sentMail: true };
        }
    }
}
