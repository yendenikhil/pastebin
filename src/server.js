const express = require('express');
const bodyParser = require('body-parser');
const http = require('http');
const app = express(http);

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static('dist'));

app.use('/api', require('./api'));
app.get('/', (req, res) => {
  res.send('Hello world!!');
});

app.listen(3000, (err) => {
  console.log('server started on :3000');
});
