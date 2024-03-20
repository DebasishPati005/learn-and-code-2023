export interface MailConfig {
    to: string;
    subject: string;
    html: string;
}

export type MailResponse = {
    message: string;
    sentMail: boolean;
};
