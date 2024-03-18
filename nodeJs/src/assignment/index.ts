import http, { Server } from 'http';
import app from './app/app';
import mongoose from 'mongoose';

const MONGO_DB_URI = `mongodb+srv://${process.env.DB_USER_NAME}:${process.env.DB_PASSWORD}@nodelearningcluster.fbds7im.mongodb.net/${process.env.DB_NAME}`;

mongoose
    .connect(MONGO_DB_URI)
    .then(() => {
        const server: Server = http.createServer(app);
        server.listen(process.env.PORT);
        console.log('DB is Connected and app is listening at PORT: ', process.env.PORT);
    })
    .catch((error: Error) => {
        console.log(error);
    });
