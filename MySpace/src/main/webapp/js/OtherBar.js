

function getParams(key) {
    let reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
};
// alert(getParams("id"));
var friendId=getParams("id");

//set url
$("#otherComment").prop("href","OtherComment.html?id="+friendId);
$("#otherFeed").prop("href","OtherFeed.html?id="+friendId);

//set decoration
let userDecoration="#DCE2F1";
axios({
    method:"get",
    url:"/HomePage/getFriendDecoration",
    data:{
        id:friendId
    }

}).then(resp=>{
    if(resp.data!="no decoration")
    {
        userDecoration=resp.data;
    }

    $("body").prop("style","background-color:"+userDecoration);
    $(".leftcolumn").prop("style","background-color:"+userDecoration);
  //  $(".rightcolumn").prop("style","background-color:"+userDecoration);

});




// change decoration
let decorations =document.getElementsByClassName("decorationColor");
for(let i=0;i<decorations.length;i++)
{
    decorations[i].addEventListener("click",function (){
        $("body").prop("style","background-color:"+decorations[i].style.backgroundColor);
        $(".leftcolumn").prop("style","background-color:"+decorations[i].style.backgroundColor);
        $(".rightcolumn").prop("style","background-color:"+decorations[i].style.backgroundColor);

        axios({
            method:"post",
            url:'/HomePage/changeFriendDecoration',

            data:{
                color:decorations[i].style.backgroundColor,
                id:friendId
            }
        }).then(resp=>{
            this.$message({
                message: '更换空间装扮！',
                type: 'success'
            });
        });
    });
}




//logout
$(".topNav .icon-logout").click(function (){
    axios({
        method:'get',
        url:''
    }).then(resp=>{
        document.location="./login.html";
    });

});

















