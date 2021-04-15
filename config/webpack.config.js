/* 수정해야함 */
const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = (env) => {
    const clientPath = path.resolve(__dirname, 'src/main/fronend');
    const outputPath = path.resolve(__dirname, (env == 'production') ? 'src/main/resources/static/out' : 'src/main/frontend/out')

    return {
        mode: !env ? 'development' : env,
        entry: {
            index: clientPath + '/event/index.js',
            dashboard: clientPath + '/event/dashboard.js',
            post: clientPath + '/event/post.js',
            statistics: clientPath + '/event/statistics.js',
            common: clientPath + '/css/common.css'
        },
        output: {
            path: outputPath,
            filename: '[name].js'
        },
        devServer: {
            contentBase: outputPath,
            publicPath: '/',
            /*host: '0.0.0.0',*/
            host: 'localhost',
            port: 8081,
            proxy: {
                '**': 'http://localhost:9980'
            },
            inline: true,
            hot: false
        },
        module: {
            rules: [{
                test: /\.js$/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    },
                }]
            }, {
                test: /\.css$/,
                use: [ MiniCssExtractPlugin.loader , 'css-loader' ],
            }]
        },
        plugins: [
            new MiniCssExtractPlugin({ filename: '[name].css'})
        ],
    }
};