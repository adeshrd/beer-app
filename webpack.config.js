const path = require('path');
const ROOT = path.resolve(__dirname, 'src/main/resources/static');
const DEST = path.resolve(ROOT, 'dist');

module.exports = {
    entry: ROOT + '/app.tsx',
    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.jsx']
    },
    cache: true,
    output: {
        path: DEST,
        filename: 'bundle.js',
        publicPath: '/dist/'
    },
    module: {
        rules: [{
            test: /\.(tsx|ts|js)$/,
            exclude: /node_modules/,
            loader: 'ts-loader',

        }]
    }
};
