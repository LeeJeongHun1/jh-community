const id = $('#bnoId').val()
let app = {
    init: function() {
        $('#comment-id-btn').click(() => {
            this.comment_add();
        })
        $('#comment_delete_btn').click((e) => {
            this.comment_delete(e);
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
                if(res.status === 200){
                    console.log("success")
                }
                if(res.status !== 200){
                    console.log("fail")
                }
                return res.json()
            })
            .then(data => {
                location.href = '/board/' + data.id  //page, option 등등 추가 예정
            })
            .catch()

    },

    comment_delete: function (e) {
        console.log(e)
    }
}
app.init();