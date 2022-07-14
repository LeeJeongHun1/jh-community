let index = {
    init: function() {
        $('#deleteBtn').click((e) => {
            this.delete();
        })
        $('#modifyBtn').click(() => {
            this.modify();
        })
        $('#password').blur(() => {
            this.passwordValid();
        })
        $('#rePassword').blur(() => {
            this.rePasswordValid();
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
            }).fail(function(data) {
                console.log(data)
            }).always(function() {
                location.href = '/board'
            })
        }
    },

    modify: function() {
        // alert ele
        const titlealertPlaceholder = document.getElementById('titleAlertPlaceholder')
        const bodyalertPlaceholder = document.getElementById('bodyAlertPlaceholder')

        // 초기화
        titlealertPlaceholder.innerHTML = '';
        bodyalertPlaceholder.innerHTML = '';

        // alert 생성
        const alert = (message, type, field) => {
            const wrapper = document.createElement('div')
            wrapper.innerHTML = [
                `<div class="alert alert-${type} d-flex align-items-center" role="alert">`,
                `<svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>`,
                `   <div>${message}</div>`,
                '</div>'
            ].join('')
            if (field === 'title') {
                titlealertPlaceholder.append(wrapper)
            } else if (field === 'body') {
                bodyalertPlaceholder.append(wrapper)
            }
            return modify;
        }

        // 데이터 추출
        let data = {
            id: $('#boardId').val(),
            title: $('#boardTitle').val().trim() !== '' ? $('#boardTitle').val() : alert('제목은 빈칸일 수 없습니다', 'warning', 'title'),
            body: $('.note-editable').text().trim() !== '' ? $('.note-editable').html() : alert('내용은 빈칸일 수 없습니다', 'warning', 'body')
        }
        console.log(data.body)
        $.ajax({
                url: "/api/board/" + data.id,
                type: "put",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
            }).done(function(data, status, error) {
                console.log(data)
                console.log(status)
                console.log(error)

                location.href = '/board/' + data.id
            }).fail(function(error) {
                console.log(error)
                location.href = '/error'
            })
            // .always(function() { // 해당 글 보기
            //     location.href = '/board/' + data.id
            // })
    },

    passwordValid: function() {
        const pw1 = document.getElementById('password').value
        const pw2 = document.getElementById('rePassword').value
        if (pw1 !== pw2 && pw2 !== '') {
            $('#rePassword').addClass('is-invalid')
        }
        if (pw1 === pw2) {
            $('#rePassword').attr('class', 'form-control')
        }
    },

    rePasswordValid: function() {
        const pw1 = document.getElementById('password').value
        const pw2 = document.getElementById('rePassword').value
        if (pw1 !== pw2) {
            $('#rePassword').addClass('is-invalid')
        } else {
            $('#rePassword').attr('class', 'form-control')
        }
    }

}
index.init();
