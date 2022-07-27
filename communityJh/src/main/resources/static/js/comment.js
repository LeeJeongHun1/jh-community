const id = $('#bnoId').val()
let app = {
    init: function() {
        $('#comment-id-btn').click(() => {
            this.comment_add();
        })
    },


    // 댓글 등록
    comment_add: function () {
        let data = {
            id: id,
            body: $('#floatingTextarea').val()
        }
        let option = {
            method: "post",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }

        fetch("/api/comment/" + data.id, option)
            .then(res => {
                console.log(res)
                res.body
                if(res.status === 200){
                    console.log("success")
                }
                if(res.status !== 200){
                    console.log("fail")
                }
            })
            .then(data => console.log(data))
            .catch()
        // $.ajax({
        //         url: "/api/board/" + data.id + "/comment",
        //         type: "post",
        //         data: JSON.stringify(data),
        //         contentType: "application/json; charset=utf-8",
        //     }).done(function(data, status, error) {
        //         console.log(data)
        //         console.log(status)
        //         console.log(error)
        //
        //         // location.href = '/board/' + data.id
        //     }).fail(function(error) {
        //         console.log(error)
        //         location.href = '/error'
        //     })
        //     // .always(function() { // 해당 글 보기
        //     //     location.href = '/board/' + data.id
        //     // })
    }

}
app.init();