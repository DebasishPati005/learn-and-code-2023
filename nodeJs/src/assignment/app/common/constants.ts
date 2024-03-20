export const CONSTANTS = {
    PASSWORD_SALT: 12,
};

export const ERROR_MESSAGE = {
    userExists: 'User Exists! \nTry with different mail id.',
    noRecordFound: 'No record found with given details!',
};

export const RESPONSE_MESSAGE = {
    newPasswordEmailSubject: 'Create New Password',
    newAccountEmailSubject: 'Account Creation Successful! ✌',
    mailSendSuccessMessage: 'Mail has been Send Successfully',
    mailSendFailureMessage: 'Mail could not send',
};

export const MailTemplates = {
    getSignUpTemplate: function (userName: string): string {
        return `<h1>Welcome ${userName}</h1>
              <div style=" background-color: #acacac;  border-radius: 10px;  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);  padding: 20px;
              margin: 20px auto;  max-width: 400px;">
                <h3 style="color: #0073e6;">Your Account is Created Successfully 🌹</h3>
                <p style="color: #333;">Your account has been successfully created. Welcome to our shopping application!</p>
              </div>
          `;
    },
};
