import { MailConfig, MailResponse } from 'assignment/app/common/types/mail';

export interface INotificationService {
    sendMail(mailConfig: MailConfig): Promise<MailResponse>;
}
