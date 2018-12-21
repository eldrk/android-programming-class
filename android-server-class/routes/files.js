var express = require('express');
var router = express.Router();

/* GET home page. */

router.post('/', function(req, res, next) {
	var number = req.body.number;
	
	switch(number) {
		case "1":
			filename =	"aqua";
			ext = ".jpg";
			break;
		case "2":
			filename =	"drug";
			ext = ".jpg";
			break;
		case "3":
			filename =	"swing";
			ext = ".jpg";
			break;
		case "4":
			filename =	"bohemian";
			ext = ".jpg";
			break;
		case "5":
			filename = "spider";
			ext = ".jpg";
			break;
		case "6":
			filename = "carol";
			ext = ".jpg";
			break;
	}

	var options = {
		root: './public/images',
		dotfiles:'deny',
		headers: {
			'x-timestamp': Date.now(),
        	'x-sent': true
		}
	};

	res.sendFile(filename+ext, options, function(err) {
		if(err) {
			next(err);
		} else {
			console.log('Sent: ', filename);
		}
	});

});

module.exports = router;
