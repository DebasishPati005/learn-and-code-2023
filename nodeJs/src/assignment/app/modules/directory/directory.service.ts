import fs from 'fs';
import path from 'path';
import { UserRequest } from '../../common/types';
import { IDirectoryService } from './directory.service.contract';

export default class DirectoryService implements IDirectoryService {
    private static directoryServiceInstance?: DirectoryService;

    private constructor() {}

    static getDirectoryServiceInstance(): DirectoryService {
        if (!this.directoryServiceInstance) {
            return new DirectoryService();
        } else {
            return this.directoryServiceInstance;
        }
    }

    public createDirectory(userReq: UserRequest): Promise<string> {
        return new Promise((resolve, reject) => {
            const directoryPath = path.join(__dirname, '../../../directories', userReq.email);
            const filePath = path.join(directoryPath, userReq.name + '.txt');
            if (!fs.existsSync(directoryPath)) {
                fs.mkdirSync(directoryPath, { recursive: true });
            }

            fs.writeFile(filePath, JSON.stringify({ ...userReq, password: undefined }), (err) => {
                if (err) {
                    reject(err);
                } else {
                    console.log('Directory created for user:');
                    resolve(filePath);
                }
            });
        });
    }
}
