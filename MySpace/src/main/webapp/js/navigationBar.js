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
            $(".searchResult").prop("style","display:none");
            document.getElementById("formationCheck").hidden=true;
            document.getElementById("noAccount").hidden=true;
            let reg=/^\d{9}$/;
            if(reg.test(this.input))
            {
                //formation check success
                axios({
                    method:"post",
                    url:'',
                    data:{
                        id:this.input
                    }
                }).then(resp=>{
                    if(resp.data=="no account")
                    {
                        //no such account
                        document.getElementById("noAccount").hidden=false;
                    }
                    else
                    {
                        //account exists
                        this.userAvatar=resp.data.avatar;
                        this.userID=resp.data.id;
                        this.userName=resp.data.name;
                        $(".searchResult").prop("style","display:block");
                    }

                });
            }
            else{
                //formation check fails
                document.getElementById("formationCheck").hidden=false;
            }

        }

    }
});

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
});


$(".decorationTrigger").click(function (){
    $("#decoration").prop("style","display:block");
});

$("#decoration").mouseleave(function (){
    $("#decoration").prop("style","display:none");
});



//set decoration
let userDecoration="#DCE2F1";
axios({
    method:"get",
    url:"/HomePage/getDecoration"
}).then(resp=>{
    if(resp.data!="no decoration")
    {
        userDecoration=resp.data;
    }
});
$("body").prop("style","background-color:"+userDecoration);
$(".leftcolumn").prop("style","background-color:"+userDecoration);
$(".rightcolumn").prop("style","background-color:"+userDecoration);




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
             url:'/HomePage/changeDecoration',
             data:{
                 color:decorations[i].style.backgroundColor
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


//get friend List
class Friend{
    constructor(avatar,name,id) {
        this.avatar=avatar;
        this.name=name;
        this.id=id;
    }
};
let friendList=[];
axios({
    method: "get",
    url:'/HomePage/getFriendList'
}).then(resp=>{
    let list=resp.data;
    list.forEach(item=>{
        friendList.push(new Friend(item.avatar,item.name,item.id));
    })
});

friendList.push(new Friend("./img.png","zhuangsan",id="1"));
friendList.push(new Friend("./img.png","lisi",id="2"));
friendList.push(new Friend("./img.png","lisi",id="3"));
friendList.push(new Friend("./img.png","lisi",id="4"));
friendList.push(new Friend("./img.png","lisi",id="5"));
friendList.push(new Friend("./img.png","lisi",id="6"));
friendList.push(new Friend("./img.png","lisi",id="7"));





let friends = $(".friendList");
friendList.forEach(item=>{
    let friend="<a target=\"_blank\" href=\""+"/OtherFeed.html?id="+item.id+"\">\n" +
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

