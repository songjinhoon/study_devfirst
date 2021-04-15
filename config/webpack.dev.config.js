const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const webpack = require('webpack');

const clientPath = path.resolve(__dirname, '../src/main/frontend');
const outputPath = path.resolve(__dirname, '../src/main/frontend/out'); //src/main/resources/static/out

module.exports = {
    mode: 'development',
    watchOptions: {
        ignored: /node_modules/
    },
    devtool: 'source-map',
    entry: {
        index: clientPath + '/event/index.js',
        dashboard: clientPath + '/event/dashboard.js',
        post: clientPath + '/event/post.js',
        statistics: clientPath + '/event/statistics.js',
        common: clientPath + '/css/common.css'
    },
    output: {
        path: outputPath,
        filename: '[name].js',
    },
    devServer: {
        contentBase: outputPath,
        publicPath: '/',
        /*host: '0.0.0.0',*/
        host: 'localhost',
        port: 9981,
        proxy: {
            '**': 'http://localhost:9980'
        },
        inline: true,
        hot: false
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [[
                            '@babel/preset-env',{
                                'useBuiltIns' : 'usage',
                                'corejs': 3,
                                "targets" : {
                                    "browsers": ["> 1%", "last 2 versions", "IE >= 10"],
                                }
                            }
                        ]],
                        plugins: [
                            '@babel/plugin-syntax-dynamic-import',
                            '@babel/plugin-transform-runtime',
                            '@babel/plugin-proposal-optional-chaining',
                            '@babel/plugin-proposal-nullish-coalescing-operator'
                        ]
                    }
                }
            },
            {
                test: /\.(css)$/,
                use: [
                    'style-loader',
                    'css-loader',
                ]
            },
            {
                test: /\.(css|less)$/,
                exclude: /node_modules/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'less-loader'
                ]
            },
            {
                test: /\.(png|jpg|gif|svg)/,
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            name: 'images/[name].[ext]?[hash]',
                            limit: 20000
                        }
                    }
                ]
            }
        ]
    },
    plugins: [
        new webpack.DefinePlugin({}),
        new CleanWebpackPlugin(),
        new MiniCssExtractPlugin({
            filename: 'app.css'
        })
    ],
};