let index = {
    init: function() {
        $('#deleteBtn').click((e) => {
            this.delete();
        })
        $('#modifyBtn').click(() => {
            console.log("???")
            this.modify();
        })
    },

    delete: function() {
        /*<![CDATA[*/
        /*]]>*/

        let reqData = {
            param: $('#bnoId').val(),
            id: /* ${boardDto.id} */ 'id',
        }
        console.log(reqData)
        if (confirm("해당 게시글을 삭제하겠습니까?")) {
            $.ajax({
                    url: "/board/" + $('#bnoId').val(),
                    type: "delete",
                    // data: "da",
                    contentType: "application/json charset=utf-8",
                    // dataType: 'json'
                    // success: function(data) {
                    //     console.log('ddd')
                    // },
                    // fail: function(data) {
                    //     console.log(data)
                    // }
                }).done(function(data) {
                    console.log(data)
                    console.log('ddd')
                })
                .always(function() {
                    location.href = '/board'
                })
        }
    },

    modify: function() {
        let data = {
            id: $('#boardId').val(),
            title: $('#boardTitle').val(),
            body: $('.note-editable').text()
        }
        console.log(JSON.stringify(data))
        $.ajax({
                url: "/api/board/" + data.id,
                type: "put",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
            }).done(function(data) {
                console.log(data)
                console.log('ddd')
            })
            // .always(function() {
            //     location.href = '/board/' + data.id
            // })
    }
}
index.init();