//addFriend dialog

let addFriendVue =  new Vue({
    el:"#addFriend",
    data(){
        return{
            dialogVisible:false,
            resultVisible:false,
            input:'',
            userAvatar:"photos/p1.jpg",
            userID:'123',
            userName:'123'
        }
    },
    methods:{

        close(){
            $(".searchResult").prop("style","display:none");
            document.getElementById("formationCheck").hidden=true;
            document.getElementById("noAccount").hidden=true;
            this.input='';
        },

        addFriend()
        {
            axios({
                method:"post",
                url:'',
                data:{
                    id:this.userID
                }
            }).then(resp=>{
                this.$message({
                    message: '请求发送成功！',
                    type: 'success'
                });
            });
        },

        searchUser()
        {
            let reg=/^\d{9}$/;
            if(reg.test(this.input))
            {

                //formation check success
                document.getElementById("formationCheck").hidden=true;
                document.getElementById("noAccount").hidden=true;
                axios({
                    method:"post",
                    url:'',
                    data:{
                        id:this.input
                    }
                }).then(resp=>{
                    if(resp.data!='')
                    {
                        //account exists
                        this.userAvatar=resp.data.avatar;
                        this.userID=resp.data.id;
                        this.userName=resp.data.name;
                        $(".searchResult").prop("style","display:block");
                    }
                    else
                    {
                        //no such account
                        $(".searchResult").prop("style","display:none");
                        document.getElementById("noAccount").hidden=false;

                    }

                });

            }
            else{
                //formation check fails
                $(".searchResult").prop("style","display:none");
                document.getElementById("formationCheck").hidden=false;
                alert(document.getElementById("noAccount"));
                document.getElementById("noAccount").hidden=false;
            }

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

friendList.push(new Friend("http://www.baidu.com","./img.png","zhuangsan"));
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
});










let message = new Vue({
    el:"#message",

    mounted() {

    },

    data(){
        return{
            dialogVisible:false,
            tableData:[{
                avatarUrl:'./photos/p1.jpg',
                name:'zhansan',
                id:'1'
            },
                {
                    avatarUrl:'./img.png',
                    name:'lisi',
                    id:'2'
                }
            ]
        }
    },

    methods:{

        getMessage()
        {
            axios({
                method:"get",
                url:""

            }).then(resp=>{
                this.tableData=resp.data();
            });
        },

        agree(row)
        {

            axios({
                method:"post",
                url:'',
                data:{
                    id:row.id,
                    type:"agree"
                }
            }).then(resp=>{
                if(resp.data=="success")
                {
                    this.$message({
                        message: '添加成功！',
                        type: 'success'
                    });
                    this.getMessage()
                }
            })
        },

        refuse(row){
            axios({
                method:"post",
                url:'',
                data:{
                    id:row.id,
                    type:"refuse"
                }
            }).then(resp=>{
                if(resp.data=="success")
                {
                    this.getMessage()
                }
            })
        }
    }
});

let checkMesg = new Vue({
    el:"#checkMesg",

    mounted()
    {
        this.messageNumber=message.tableData.length;
    },


    data(){
        return{
            messageNumber:0

        }
    },

    methods:{
        handleClick()
        {
            message.dialogVisible=true;
        }
    }
});

