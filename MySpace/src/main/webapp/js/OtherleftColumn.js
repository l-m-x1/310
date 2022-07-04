//set avatar and username
new Vue ({
    el: "#avatar",
    mounted(){
        this.getUserInfo();
    },

    data(){
        return {
            userAvatar:'/defaultAvatar.png',
            userName:''
        }
    },

    methods: {

        getUserInfo(){
            axios({
                method:"post",
                url:'/Access/getUserInfo',
                data:{id :friendId}

            }).then(resp=>{
                this.userAvatar=resp.data.userAvatar;
                this.userName=resp.data.userName;
                $("#avatar el-avatar img").prop("src",this.userAvatar)
                $("#userName").text(this.userName);
            });


        },

        errorHandler() {
            return true
        }
    }
});