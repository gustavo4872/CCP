public static String formataDados(String dado){
   return dado.replaceAll("[^0-9]+", "");
}
public static String formataDados(String dado){
   dado = dado.replaceAll("\\.",""); 
   return dado;
}
"[^0-9]+" retira tudo que não for numero
"\\." retira pontos 
"." retira tudo