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
                url:'/HomePage/addFriend',
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
                    url:'/HomePage/selectFriend',
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
let isBlock=false;
$(".friendListTrigger").click(function (){
    if(isBlock)
    {
        $("#friendList").prop("style","display:none");
        isBlock=false;
    }
    else
    {
        $("#friendList").prop("style","display:block");
        isBlock=true;
    }

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
    url:"/HomePage/getMyDecoration"
}).then(resp=>{
    if(resp.data!="no decoration")
    {
        userDecoration=resp.data;
        console.log(userDecoration)
    }
    $("body").prop("style","background-color:"+userDecoration);
    $(".leftcolumn").prop("style","background-color:"+userDecoration);
// $(".rightcolumn").prop("style","background-color:"+userDecoration);

});




// change decoration
 let decorations =document.getElementsByClassName("decorationColor");
 for(let i=0;i<decorations.length;i++)
 {
     decorations[i].addEventListener("click",function (){
         $("body").prop("style","background-color:"+decorations[i].style.backgroundColor);
         $(".leftcolumn").prop("style","background-color:"+decorations[i].style.backgroundColor);
         // $(".rightcolumn").prop("style","background-color:"+decorations[i].style.backgroundColor);

         axios({
             method:"post",
             url:'/HomePage/changeMyDecoration',
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
new Vue({
   el:"#friendList",

    mounted(){
        // this.getFriendList();
        this.getFriendList();
        console.log(10000);
        // this.fileList.push(new Friend('/photos/c6a374b6-17d5-4177-b80b-a79e61488b4d.jpg','李四','100000013'));
        // this.fileList.push(new Friend('/photos/ff03cc60-6036-4294-94df-475ae86ca01e.jpg','王五','100000014'));
        // this.fileList.push(new Friend('/photos/6b0eef7a-66e1-4789-923b-ed00b5376933.jpg','小明','100000015'));
        console.log(10000);
        // console.log(this.fileList);
    },

    data(){
       return{
           // friendList:[]
           friendList:[new Friend('/photos/c6a374b6-17d5-4177-b80b-a79e61488b4d.jpg','李四','100000013'),
               new Friend('/photos/ff03cc60-6036-4294-94df-475ae86ca01e.jpg','王五','100000014'),
               new Friend('/photos/6b0eef7a-66e1-4789-923b-ed00b5376933.jpg','小明','100000015')]
       }
    },

    methods: {

       accessFriend(row)
       {
           axios({
               method:"post",
               url:"/Access/getPermission",
               data:{
                   id:row.id
               }
           }).then(resp=>{
               if(resp.data=="permission")
               {
                   location.href="/OtherFeed.html?id="+row.id;
               }
               else
               {
                   this.$message.error('好友未为你设置访问权限！');
               }
           })
       },

        deleteFriend(row)
        {
            this.$confirm('是否删除好友?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios({
                    method:"post",
                    url:"/HomePage/deleteFriend",
                    data:{
                        id:row.id
                    }
                }).then(resp=>{
                    this.getFriendList();
                });
            }).catch(() => {

            });

        },
        getFriendList(){

             axios({
                method: "get",
                url:'/HomePage/getFriendList'
            }).then(resp=>{
                // let resultList=resp.data;
                // // for(let i=0;i<resultList.length;i++)
                // //     this.fileList.push(new Friend(resultList[i].avatar,resultList[i].name,resultList[i].id));
                //  this.friendList=resp.data;

            });
        }
    }
});



let message = new Vue({
    el:"#message",

    mounted() {
        this.getMessage();
    },

    data(){
        return{
            dialogVisible:false,
            tableData:[{
                avatar:'./photos/p1.jpg',
                name:'zhansan',
                id:'1'
            },
                {
                    avatar:'./img.png',
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
                url:"/HomePage/getAddFriMsg"
            }).then(resp=>{
                this.tableData=resp.data;
            });
        },

        agree(row)
        {

            axios({
                method:"post",
                url:'/HomePage/accept',
                data:{
                    id:row.id
                }
            }).then(resp=>{
                    this.$message({
                        message: '添加成功！',
                        type: 'success'
                    });
                    this.getMessage();
            })
        },

        refuse(row){
            axios({
                method:"post",
                url:'/HomePage/refuse',
                data:{
                    id:row.id
                }
            }).then(resp=>{
                    this.getMessage();
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

