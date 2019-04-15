import getTest from './get-test/getTest';

export default (app) => {
    app.get('/get-test', getTest);
}
