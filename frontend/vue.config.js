module.exports = {
    devServer : {
        proxy : {
            '/' : {
                target : 'localhost:8081/',
                ws : true,
                changeOrigin : true
            }
        }
    },
    "transpileDependencies": [
        "vuetify"
    ]
}
