const path = require("path");
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
    mode: 'development',
    devtool: 'inline-source-map',
    watchOptions: {
        ignored: /node_modules/
    },
    /*devServer: {
        /!* package.json에 명시하지 않고 여기에 정의 *!/
        contentBase: path.resolve(__dirname, './dist'),
        port: 3000,
        host: 'localhost',
        inline: true,
        hot: true,
        historyApiFallback: true,
        compress: true,
        proxy: {
            "**": "http://localhost:9980"
        }
    },*/
    // 의존성의 시작점
    entry: {
        index: "./js/index.js", /* 상대경로 */
		dashboard: './js/dashboard.js',
		post: './js/post.js'
    },
    // 번들링 된 결과물이 위치하는 곳
    output: {
        filename: "[name].js",
        path: path.resolve(__dirname, './dist'), /* 현재 프로젝트 디렉토리에서 ../dist에 위치 */
        clean: true
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/, //node_modules폴더는 패키지 폴더니까 제외처리
                use: ["babel-loader"]    //es6 -> es5
            }
/*            {
                test: /\.css$/, //웹팩은 모듈을 다루기 때문에 css파일도 .js로 바꿔준다.
                use: ["style-loader", "css-loader"]
            }*/
        ]
    },
/*    plugins: [
        /!*new webpack.NamedModulesPlugin() //브라우저에서 HMR 에러발생시 module name 표시*!/
        new HtmlWebpackPlugin({
            title: 'Development'
        })
    ]*/
};