let index = {
    init: function(){
        $("#btn-save").on("click", ()=> {
            this.save();
        });

        $("#btn-delete").on("click", ()=> {
            this.deleteById();
        });

        $("#btn-update").on("click", ()=> {
            this.update();
        });
    },

    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        // ajax 호출 시 default가 비동기 호출
        $.ajax({
            // 글 작성 수행 요청
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청으로 서버에 응답이 왔을 때 default는 문자열, 만약 형식이 json이면 js Object로 변환

        }).done(function(resp){
            // 성공시
            alert("글 작성이 완료되었습니다.");
            location.href="/";

        }).fail(function(error){
            // 실패 시
            alert(JSON.stringify(error));

        });
    },

    deleteById: function(){
        let id =$("#id").text();

        $.ajax({
            // 글 삭제 수행
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json",
            contentType: "application/json; charset=utf-8"

        }).done(function(resp){
            // 성공시
            alert("글 삭제가 완료되었습니다.");
            location.href="/";

        }).fail(function(error){
            // 실패 시
            alert(JSON.stringify(error));

        });
    },

    update: function(){
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        // ajax 호출 시 default가 비동기 호출
        $.ajax({
            // 글 작성 수행 요청
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청으로 서버에 응답이 왔을 때 default는 문자열, 만약 형식이 json이면 js Object로 변환

        }).done(function(resp){
            // 성공시
            alert("글 수정이 완료되었습니다.");
            location.href="/";

        }).fail(function(error){
            // 실패 시
            alert(JSON.stringify(error));

        });
    },

};

index.init();