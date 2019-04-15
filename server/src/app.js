import express from 'express';
import bodyParser from 'body-parser';
import compression from 'compression';
import cors from 'cors';

import routers from './routers';

const app = express();

const PORT = process.env.PORT || 8080 ;

const router = routers(app);

app.use(
    bodyParser.urlencoded({ extended : true }),
    bodyParser.json(),
    cors()
    );

app.listen(PORT, () => {
    console.log(`listening server port on ${PORT}!`);
});
