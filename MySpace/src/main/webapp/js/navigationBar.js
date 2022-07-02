//addFriend dialog

let addFriendVue =  new Vue({
    el:"#addFriend",
    data(){
        return{
            dialogVisible:false,
            resultVisible:false,
            input:'',
            userAvatar:"./img.png",
            userID:'',
            userName:''
        }
    },
    methods:{

        close(){
            $(".searchResult").prop("style","display:none");
            this.input='';
        },

        addFriend()
        {
            alert(1);
        },

        searchUser()
        {
            $(".searchResult").prop("style","display:block");
        }

    }
})

//set button
$(".friendListTrigger").mouseenter(function (){
    $(".friendList").prop("style","display:block");
});

$(".friendList").mouseleave(function (){
    $(".friendList").prop("style","display:none");
});

$("#settingTrigger").click(function ()
{
    $("#setting").prop("style","display:block");
});

$("#setting").mouseleave(function (){
    $("#setting").prop("style","display:none");
});

$(".addFriendTrigger").click(function (){
    addFriendVue.dialogVisible=true;
})



//logout
$(".topNav .icon-logout").click(function (){
    axios({
        method:'get',
        url:''
    }).then(resp=>{
        document.location="./login.html";
    });

});


//get friend List
class Friend{
    constructor(url,avatar,name) {
        this.url=url;
        this.avatar=avatar;
        this.name=name;
    }
};
let friendList=[];
axios({
    method: "get",
    url:''
}).then(resp=>{
    let list=resp.data;
    list.forEach(item=>{
        friendList.push(new Friend(item.url,item.avatar,item.name));
    })
});

friendList.push(new Friend("www.baidu.com","./img.png","zhuangsan"));
friendList.push(new Friend("","./img.png","lisi"));
friendList.push(new Friend("","./img.png","lisi"));
friendList.push(new Friend("","./img.png","lisi"));
friendList.push(new Friend("","./img.png","lisi"));
friendList.push(new Friend("","./img.png","lisi"));
friendList.push(new Friend("","./img.png","lisi"));

let friends = $(".friendList");
friendList.forEach(item=>{
    let friend="<a target=\"_blank\" href=\""+item.url+"\">\n" +
        "        <div class=\"friend\">\n" +
        "\n" +
        "            <img class=\"friendAvatar\" src=\""+item.avatar+"\"  width=\"50\" height=\"50\" style=\"float: left\">\n" +
        "\n" +
        "            <div class=\"friendName\">"+item.name+"</div>\n" +
        "\n" +
        "        </div>\n" +
        "    </a>";
    friends.append(friend);
})