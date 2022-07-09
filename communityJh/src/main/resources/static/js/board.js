let index = {
    init: function() {
        $('#deleteBtn').click((e) => {
            console.log(e)
            this.delete();
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
                    location.href = '/boards/'
                })
        }
    }
}
index.init();