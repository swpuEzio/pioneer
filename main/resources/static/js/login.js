var num = $("#num")
var truename = $("#name")
$("#check").click(function () {
    var numValue = num.val()
    var truenameValue = truename.val()
    console.log(numValue)
    console.log(truenameValue)
    $.ajax({
        type: 'get',
        url: '/checkIDCard',
        dataType: 'json',
        data:{
            num:numValue,
            name:truenameValue
        },
        success:function (res) {

            console.log("进入服务器")
            console.log(res)
        },
        error: function () {
            alert("错误")
        }
    })
})
// function imgChange(e) {
//     var dom = document.getElementById("avatarChange").files[0];
//     // if(!/mp4\/\w+/.test(dom.type)){
//     //     alert("请选择图片")
//     //     return false;
//     // }
//
//     var reader = new FileReader();
//     reader.onload = function (file) {
//         return function (e) {
//             var info = this.result;
//             console.log(info);
//             $.ajax({
//                 type: 'post',
//                 url: '/upVideo',
//                 dataType: 'multipart',
//                 data: {
//                     info: info
//                 },
//                 success:function (data) {
//                     if (data['status'] != 200){
//                         alert("更新头像出了点错误，再试一次吧")
//                     }else {
//                         alert("更新成功");
//                         window.location.reload();
//                     }
//
//                 },
//                 error:function () {
//                     console.log("上传失败")
//                 }
//
//             })
//         }
//     }(e.target.files[0]);
//     reader.readAsDataURL(e.target.files[0]);
//
// }

function imgChange(file) {

    var formData = new FormData()
    var temp = file.files[0]
    if (temp){
        formData.append('file',temp)

        $.ajax({
            url:"/upVideo",
            type:"POST",
            data: formData,
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            success: function(result){
               console.log(result)
            }
        })
    }
}
var intervalId = setInterval(function () {
    $.get("http://localhost:1022/item/percent", {}, function (data) {
        console.log(data);
        var percent = data;
        if (percent >= 100) {
            clearInterval(intervalId);
            percent = 100;//不能大于100
        }
        $('#id_percent').val(percent);
    }, "json");
}, 100);
$("#like").click(function () {
    $.ajax({
        type: 'get',
        url: '/like',
        dataType: 'json',
        data:{
           articleID:"123456"
        },
        success:function (data) {
            console.log(data['status'])
        },
        error:function () {
            console.log("请求失败")
        }
    })
})

