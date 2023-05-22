let index = {
    init: function(){
        $("#btn-save").on("click", ()=> { //function 사용을 안 하는 이유: this 바인딩을 위해
            this.save();
        });
        /*$("#btn-login").on("click", ()=> { //function 사용을 안 하는 이유: this 바인딩을 위해
            this.login();
        });*/
    },

    save: function(){
        //alert("user의 save함수 호출");
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);

        // ajax 호출 시 default가 비동기 호출
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청으로 서버에 응답이 왔을 때 default는 문자열, 만약 형식이 json이면 js Object로 변환

        }).done(function(resp){
            // 성공시
            alert("회원가입이 완료되었습니다.");
            //console.log(resp);
            location.href="/";

        }).fail(function(error){
            // 실패 시
            alert(JSON.stringify(error));

        }); // ajax 통신을 이용해 3개의 파라미터를 json으로 파싱하여 insert 요청
    },

    /*login: function(){
            //alert("user의 save함수 호출");
            let data = {
                username: $("#username").val(),
                password: $("#password").val()
            };

            //console.log(data);

            // ajax 호출 시 default가 비동기 호출
            $.ajax({
                // 회원가입 수행 요청
                type: "POST",
                url: "/api/user/login",
                data: JSON.stringify(data), // http body 데이터
                contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
                dataType: "json" // 요청으로 서버에 응답이 왔을 때 default는 문자열, 만약 형식이 json이면 js Object로 변환

            }).done(function(resp){
                // 성공시
                alert("로그인이 완료되었습니다.");
                //console.log(resp);
                location.href="/";

            }).fail(function(error){
                // 실패 시
                alert(JSON.stringify(error));

            }); // ajax 통신을 이용해 3개의 파라미터를 json으로 파싱하여 insert 요청
    }*/
};

index.init();