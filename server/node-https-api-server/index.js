var express = require('express');
var bodyParser = require('body-parser');
var https = require('https');
var fs = require('fs');

var app = express();


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.get('/*', function(req, res) {
	print_request(req);
	res.status(200).json({result: 0})
});
app.post('/*', function(req, res) {
	print_request(req);
	res.status(200).json({result: 0})
});

function print_request(req) {
	console.log('Path: ' + req.path)
	if (req.method == 'POST') {
		console.log(req.body)
	}
	console.log(JSON.stringify(req.headers));
}

var https_opts = {
    key: fs.readFileSync('https/server_key.pem'),
    cert: fs.readFileSync('https/server.crt'),
    ca: fs.readFileSync('https/ca.crt')
};

https.createServer(https_opts, app).listen(10443, function () {
    console.log('Started on 10443')
});