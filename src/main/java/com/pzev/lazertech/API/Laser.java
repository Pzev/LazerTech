package com.pzev.lazertech.API;


import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Laser {

    private static boolean archiveLazers = false;
    private static ArrayList<Laz> ALLLAZERS = new ArrayList<Laz>();

    public static void archiveLazers(boolean b)
    {
        archiveLazers = b;
    }


    public static void newLazer(int x1, int y1, int z1, int x2, int y2, int z2, int color1, int color2, int size) {
        if (size >= 10) size = 10;

        double x = Math.abs(x1 - x2);
        double y = Math.abs(y1 - y2);
        double z = Math.abs(z1 - z2);

        double c = Math.sqrt(x * x + y * y + z * z);
        // not currently used: double xzDist = Math.sqrt(x*x + z*z);

        double upAngle = Math.asin(y / c);
        double sideAngle = Math.atan(x / z);

        glTranslated(x1, y1, z1);
        glRotated(upAngle, 1.0f, 0, 1.0f);
        glRotated(sideAngle, 0, 1.0f, 0);

        // switch to use correct texture

        //switch case to choose color

        size /= 10;

        for (int i = 0; i < c; i++)
        {
            if (c - i > 1)
            {

                glBegin(GL_QUADS);
                //bottom plane
                glTexCoord2f(0.0f, (float) (0.5 - (size / 2)));
                glVertex3f((float) (0.5 - (size / 2)), -(size / 2), i);
                glTexCoord2f(1.0f, (float) (0.5 - (size / 2)));
                glVertex3f((float) (0.5 - (size / 2)), -(size / 2), i + 1);
                glTexCoord2f(1.0f, (float) (0.5 + (size / 2)));
                glVertex3f((float) (0.5 + (size / 2)), -(size / 2), i + 1);
                glTexCoord2f(0.0f, (float) (0.5 + (size / 2)));
                glVertex3f((float) (0.5 + (size / 2)), -(size / 2), i);

                //top plane
                glTexCoord2f(0.0f, (float) (0.5 + (size / 2)));
                glVertex3f((float) (0.5 - (size / 2)), (size / 2), i);
                glTexCoord2f(1.0f, (float) (0.5 + (size / 2)));
                glVertex3f((float) (0.5 - (size / 2)), (size / 2), i + 1);
                glTexCoord2f(1.0f, (float) (0.5 - (size / 2)));
                glVertex3f((float) (0.5 + (size / 2)), (size / 2), i + 1);
                glTexCoord2f(0.0f, (float) (0.5 - (size / 2)));
                glVertex3f((float) (0.5 + (size / 2)), (size / 2), i);
                glEnd();

            }
            else if(c - i > 0)
            {
                //bottom plane
                glTexCoord2f(0.0f, (float) (0.5 - (size / 2)));
                glVertex3f((float) (0.5 - (size / 2)), -(size / 2), i);
                glTexCoord2f((float) c - i, (float) (0.5 - (size / 2)));
                glVertex3f((float) (0.5 - (size / 2)), -(size / 2), (float) c);
                glTexCoord2f((float) c - i, (float) (0.5 + (size / 2)));
                glVertex3f((float) (0.5 + (size / 2)), -(size / 2), (float) c);
                glTexCoord2f(0.0f, (float) (0.5 + (size / 2)));
                glVertex3f((float) (0.5 + (size / 2)), -(size / 2), i);

                //top plane
                glTexCoord2f(0.0f, (float) (0.5 + (size / 2)));
                glVertex3f((float) (0.5 - (size / 2)), (size / 2), i);
                glTexCoord2f((float) c - i, (float) (0.5 + (size / 2)));
                glVertex3f((float) (0.5 - (size / 2)), (size / 2), (float) c);
                glTexCoord2f((float) c - i, (float) (0.5 - (size / 2)));
                glVertex3f((float) (0.5 + (size / 2)), (size / 2), (float) c);
                glTexCoord2f(0.0f, (float) (0.5 - (size / 2)));
                glVertex3f((float) (0.5 + (size / 2)), (size / 2), i);
                glEnd();

            }
        }

        if(archiveLazers)
        {
            Laz laz1 = new Laz();
            laz1.laz(x1, y1, z1, x2, y2, z2, color1, color2, size, ALLLAZERS.size() + 1);
            ALLLAZERS.add(laz1);
        }
    }

    public static int returnLatestID()
    {
        return ALLLAZERS.size();
    }


    public static void renderArchived()
    {
        for(int q = 0; q < ALLLAZERS.size(); q++)
        {
            Laz temp1 = ALLLAZERS.get(q);

            int size = temp1.getthickness();

            int[] B1Coords = temp1.getBlock1Coords();
            int[] B2Coords = temp1.getBlock2Coords();

            int x1 = B1Coords[0];
            int y1 = B1Coords[1];
            int z1 = B1Coords[2];

            int x2 = B2Coords[0];
            int y2 = B2Coords[1];
            int z2 = B2Coords[2];

            int color1 = temp1.getcolor1();
            int color2 = temp1.getcolor2();


            if (size >= 10) size = 10;

            double x = Math.abs(x1 - x2);
            double y = Math.abs(y1 - y2);
            double z = Math.abs(z1 - z2);

            double c = Math.sqrt(x * x + y * y + z * z);
            // not currently used: double xzDist = Math.sqrt(x*x + z*z);

            double upAngle = Math.asin(y / c);
            double sideAngle = Math.atan(x / z);

            glTranslated(x1, y1, z1);
            glRotated(upAngle, 1.0f, 0, 1.0f);
            glRotated(sideAngle, 0, 1.0f, 0);

            // switch to use correct texture

            //switch case to choose color

            size /= 10;

            for (int i = 0; i < c; i++)
            {
                if (c - i > 1)
                {

                    glBegin(GL_QUADS);
                    //bottom plane
                    glTexCoord2f(0.0f, (float) (0.5 - (size / 2)));
                    glVertex3f((float) (0.5 - (size / 2)), -(size / 2), i);
                    glTexCoord2f(1.0f, (float) (0.5 - (size / 2)));
                    glVertex3f((float) (0.5 - (size / 2)), -(size / 2), i + 1);
                    glTexCoord2f(1.0f, (float) (0.5 + (size / 2)));
                    glVertex3f((float) (0.5 + (size / 2)), -(size / 2), i + 1);
                    glTexCoord2f(0.0f, (float) (0.5 + (size / 2)));
                    glVertex3f((float) (0.5 + (size / 2)), -(size / 2), i);

                    //top plane
                    glTexCoord2f(0.0f, (float) (0.5 + (size / 2)));
                    glVertex3f((float) (0.5 - (size / 2)), (size / 2), i);
                    glTexCoord2f(1.0f, (float) (0.5 + (size / 2)));
                    glVertex3f((float) (0.5 - (size / 2)), (size / 2), i + 1);
                    glTexCoord2f(1.0f, (float) (0.5 - (size / 2)));
                    glVertex3f((float) (0.5 + (size / 2)), (size / 2), i + 1);
                    glTexCoord2f(0.0f, (float) (0.5 - (size / 2)));
                    glVertex3f((float) (0.5 + (size / 2)), (size / 2), i);
                    glEnd();

                }
                else if(c - i > 0)
                {
                    //bottom plane
                    glTexCoord2f(0.0f, (float) (0.5 - (size / 2)));
                    glVertex3f((float) (0.5 - (size / 2)), -(size / 2), i);
                    glTexCoord2f((float) c - i, (float) (0.5 - (size / 2)));
                    glVertex3f((float) (0.5 - (size / 2)), -(size / 2), (float) c);
                    glTexCoord2f((float) c - i, (float) (0.5 + (size / 2)));
                    glVertex3f((float) (0.5 + (size / 2)), -(size / 2), (float) c);
                    glTexCoord2f(0.0f, (float) (0.5 + (size / 2)));
                    glVertex3f((float) (0.5 + (size / 2)), -(size / 2), i);

                    //top plane
                    glTexCoord2f(0.0f, (float) (0.5 + (size / 2)));
                    glVertex3f((float) (0.5 - (size / 2)), (size / 2), i);
                    glTexCoord2f((float) c - i, (float) (0.5 + (size / 2)));
                    glVertex3f((float) (0.5 - (size / 2)), (size / 2), (float) c);
                    glTexCoord2f((float) c - i, (float) (0.5 - (size / 2)));
                    glVertex3f((float) (0.5 + (size / 2)), (size / 2), (float) c);
                    glTexCoord2f(0.0f, (float) (0.5 - (size / 2)));
                    glVertex3f((float) (0.5 + (size / 2)), (size / 2), i);
                    glEnd();

                }
            }
        }
    }
}
