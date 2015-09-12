organization := "me.gvdm"

name := "Basic Lift"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
                 )

libraryDependencies ++= {
  val liftVersion = "3.0-M6"
  val liftEdition = liftVersion.substring(0,3)
  Seq(
    "net.liftweb"		%% "lift-webkit"			% liftVersion		% "compile",
    "net.liftmodules"	        %% ("lift-jquery-module_"+liftEdition)  % "2.9",
    "javax.servlet"		% "javax.servlet-api"			% "3.0.1"		% "provided"
  )
}


enablePlugins(JettyPlugin)

// Remove Java directories, otherwise sbteclipse generates them
unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(Seq(_))

unmanagedSourceDirectories in Test <<= (scalaSource in Test)(Seq(_))

EclipseKeys.withSource := true

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

unmanagedResourceDirectories in Compile <+= (baseDirectory) { _ / "src/main/webapp" }

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/test/webapp" }
