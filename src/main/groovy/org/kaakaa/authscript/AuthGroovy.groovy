package org.kaakaa.authscript

import org.apache.commons.cli.*

import groovy.ui.GroovyMain

class AuthGroovy {
	public static void main(args){
		Option opt_username = OptionBuilder.withLongOpt('username').withDescription('username for basic auth').hasArg().withArgName('username').create('u')
		Option opt_password = OptionBuilder.withLongOpt('password').withDescription('password for basic auth').hasArg().withArgName('password').create('p')
		Options opts = new Options()
		opts.addOption(opt_username).addOption(opt_password)

		CommandLineParser parser = new PosixParser()
		CommandLine cl = parser.parse(opts,args,false)

		if(cl.hasOption('username') && cl.hasOption('password')){
			def username = cl.getOptionValue('username')
			def password = cl.getOptionValue('password')

			Authenticator.setDefault(new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,password.toCharArray());
						}
					});
		}
		GroovyMain.main(args);
	}
}
