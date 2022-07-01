//set avatar and username
new Vue ({
    el: "#avatar",
    mounted(){
        this.getUserInfo();
    },

    data(){
        return {
            userAvatar:'',
            useName:''
        }
    },

    methods: {

        getUserInfo(){
            axios({
                method:"get",
                url:'',

            }).then(resp=>{
                this.userAvatar=resp.data.userAvatar;
                this.userName=resp.data.userName;
            });

            $("#avatar el-avatar img").prop("src",this.userAvatar)
            $("#userName").text(this.userName);
        },

        errorHandler() {
            return true
        }
    }
});