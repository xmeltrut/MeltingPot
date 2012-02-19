MeltingPot compresses and combines JavaScript files into one single, small file which can be used to reduce load times and save bandwidth on production environments.

To see how it works, use the sample files:

	scala MeltingPot.scala sample1.js sample2.js

This will create a JavaScript file named output.js with the two files combined and compressed. There is no limit in the number of filenames you can supply.