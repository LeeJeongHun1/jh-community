// const id = $('#bnoId').val();


// 댓글 등록
async function comment_add(){
    // console.log(commentId)
    let body = $('#floatingTextarea').val();
    if(body === '') {
        alert('댓글 내용을 입력하세요')
        return;
    }
    let option = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body)
    }
    const res = await fetch("/api/comment/" + id, option)
    if(res.status === 200) {
        location.href = '/board/' + id
    }else {
        alert('댓글 등록을 실패 했습니다')
    }
}

async function comment_modify(commentId){
    let body = $('#floatingTextarea_' + commentId).val()
    if(body.trim() === '') {
        alert('댓글을 입력하세요')
    }else {
        let option = {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({body: body})
        }
        const res = await fetch("/api/comment/" + commentId, option)
        if(res.status === 200){
            const data = await res.json()
            location.href = '/board/' + id
        }else{
            console.log(res)
        }
    }

    // const res = await fetch("/api/comment/" + commentId, option)

}

// 댓글 삭제
async function comment_delete(commentId){
    let option = {
        method: "delete",
        headers: {
            'Content-Type': 'application/json',
        }
    }

    const res = (await fetch("/api/comment/" + commentId, option))
    if(res.status === 200){
        console.log(id)
        location.href = '/board/' + id
    }else {
      alert('댓글을 삭제하지 못 했습니다')
    }
}


function comment_edit(commentId){
    let commentEle = document.querySelector("div[data-id=comment_" + commentId +"]").querySelector("p")

    $(commentEle).html('<div id="comment_edit_' + commentId + '"><textarea class="form-control" id="floatingTextarea_' + commentId + '" style="height: 80px; resize: none">'+ commentEle.innerText +'</textarea>' +
        '<div class="pb-1 mb-1 list-group list-group-horizontal">' +
        '<button class="list-group-item" onclick="comment_modify(' + commentId + ')">수정</button>' +
        '<button class="list-group-item" onclick="edit_hide(' + commentId + ', \'' + commentEle.innerText + '\')">취소</button>' +
        '</div></div>')

}

function edit_hide(commentId, comment) {
    $('#comment_edit_' + commentId).hide()
    document.querySelector("div[data-id=comment_" + commentId +"]").querySelector("p").innerText = comment
}
