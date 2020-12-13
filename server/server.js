const http = require('http');
const https = require('https');
const fs = require('fs');
const app = require('./app');

const privateKey = fs.readFileSync('my_key.key', 'utf-8');
const certificate = fs.readFileSync('my_key.crt', 'utf-8');

const credentials = {key: privateKey, cert: certificate};

const port = process.env.PORTHTTP || 3003;
const portS = process.env.PORTHTTPS || 3000;

const httpServer = http.createServer(app);
const httpsServer = https.createServer(credentials, app);

httpServer.listen(port);
httpsServer.listen(portS);