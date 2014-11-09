#!/bin/bash

lein cljsbuild once
lein garden once

gzip -9 -c resources/public/js/photo.js > resources/public/js/photo.js.gz

s3cmd del --recursive --force s3://tihancock.co.uk

s3cmd put -m text/javascript --add-header "Content-Encoding:gzip" resources/public/js/photo.js.gz s3://tihancock.co.uk/js/photo.js.gz
s3cmd put -m text/css resources/public/css/style.css s3://tihancock.co.uk/css/style.css
s3cmd put -m text/html resources/public/index.html s3://tihancock.co.uk/index.html
s3cmd put --recursive resources/public/images s3://tihancock.co.uk/

