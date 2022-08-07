const id = $('#bnoId').val();
let index = {
    init: function() {
        $('#deleteBtn').click(() => {
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


    delete: async function() {
        if (confirm("해당 게시글을 삭제하겠습니까?")) {
            let option = {
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json',
                }
            }
            const res = await fetch("/api/board/" + id, option)
            if(res.status === 200) {
                location.href = '/board/'
            }else {
                alert('게시글 삭제를 실패 했습니다')
            }
        }
    },

    modify: function() {
        // alert ele
        const titleAlertPlaceholder = document.getElementById('titleAlertPlaceholder')
        const bodyAlertPlaceholder = document.getElementById('bodyAlertPlaceholder')

        // 초기화
        titleAlertPlaceholder.innerHTML = '';
        bodyAlertPlaceholder.innerHTML = '';

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
                titleAlertPlaceholder.append(wrapper)
            } else if (field === 'body') {
                bodyAlertPlaceholder.append(wrapper)
            }
            return false
        }

        // 데이터 추출
        var title = $('#boardTitle').val().trim() !== '' ? $('#boardTitle').val() : alert('제목은 빈칸일 수 없습니다', 'warning', 'title')
        var body = $('.note-editable').text().trim() !== '' ? $('.note-editable').html() : alert('내용은 빈칸일 수 없습니다', 'warning', 'body')

        if (title && body)
            $('#modifyForm').submit();
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
    },

}
index.init();


async function board_like() {
    let like = $('#heart').attr('data-like');
    console.log(like)
    let option = {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            "like": like === 'true' ? true : false,
            "boardId": id
        })
    }
    console.log(option.body)
    const res = await fetch("/api/board/" + id + "/like", option)
    if(res.status === 200) {
        // location.href = '/board/'
        const dtoData = await res.json()

        $('#heart').attr('data-like', dtoData.userLike);
        $('#likeCount').html(dtoData.likeList.length)
        if (dtoData.userLike) {
            $('#heart').attr('class', 'bi bi-heart-fill')
        } else {
            $('#heart').attr('class', 'bi bi-heart')
        }
    }else {
        alert('좋아요 등록을 실패 했습니다')
    }

}

async function board_delete() {

}