/* run these commands to clean up the db of stocks that won't work with yahoo finance*/
delete from stockTxs where ticker in ('fbm', 'fil', 'fos');
delete from stocks where ticker in  ('fbm', 'fil', 'fos');