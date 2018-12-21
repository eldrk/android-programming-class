var express = require('express');
var router = express.Router();

/* GET home page. */

router.post('/', function(req, res, next) {
	var number = req.body.number;
	var title = "";
	var director = "";
	var actor = "";
	var category = "";
	var runningTime = "";
	var openDate = "";

	switch(number) {
		case "1":
			title = "아쿠아맨"
			director = ["제임스 완"];
			actor = ["제이슨 모모아","앰버 허드","니콜키드만","패트릭 윌슨","윌렘 데포","돌프 룬드그랜"];
			category = ["액션"];
			runningTime = 143;
			openDate = "2018.12.19";
			break;
		case "2":
			title = "마약왕";
			director = ["우민호"];
			actor = ["송강호","조정석","배두나"];
			category = ["범죄","드라마"];
			runningTime = 139;
			openDate = "2018.12.19";
			break;
		case "3":
			title = "스윙키즈";
			director = ["김형철"];
			actor = ["도경수","자레드 그라임스","박혜수","오정세","김민호"];
			category = ["드라마"];
			runningTime = 133;
			openDate = "2018.12.19";
			break;
		case "4":
			title = "보헤미안랩소디";
			director = ["브라이언 싱어"];
			actor = ["레미 맬렉","루시 보인턴","귈림 리","밴 하디","조셈 마젤로"];
			category = ["드라마"];
			runningTime = 134;
			openDate = "2018.10.31";
			break;
		case "5":
			title = "스파이더맨";
			director = ["밥 퍼시케티","피터 램지","로드니 로스맨"];
			actor = ["사매익 무어","헤일리 스태인필드","니롤라스 케이지","제이크 존슨"];
			category = ["액션","가족","애니메이션"];
			runningTime = 117;
			openDate = "2018.12.12";
			break;
		case "6":
			title = "캐롤"
			director = ["토드 헤인즈"];
			actor = ["케이트 블란쳇","루나 마라","카일 챈들러","제이크 레이시","사리 폴슨"];
			category = ["드라마"];
			runningTime = 118;
			openDate = "2016.02.04";
			break;
	}
	 
	
	

	res.json({"number":number, "title":title, "director":director, "actor":actor, "category":category, "runningTime":runningTime, "openDate":openDate});

});

module.exports = router;
