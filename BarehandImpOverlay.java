package net.runelite.client.plugins.barehandimp;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.Perspective;
import net.runelite.client.ui.overlay.*;
import net.runelite.api.coords.LocalPoint;

import javax.inject.Inject;
import java.awt.*;

public class BarehandImpOverlay extends Overlay {
    private final Client client;
    private final BarehandImpConfig config;

    @Inject
    public BarehandImpOverlay(Client client, BarehandImpConfig config) {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        for (NPC npc : client.getNpcs()) {
            if (npc.getName() != null && isHighlightableImp(npc)) {
                if (config.isEnableBodyHighlight()) {
                    renderHighlight(graphics, npc);
                }
                if (config.isEnableTileHighlight()) {
                    renderTileHighlight(graphics, npc);
                }
            }
        }
        return null;
    }

    private boolean isHighlightableImp(NPC npc) {
        String name = npc.getName();
        int hunterLevel = client.getRealSkillLevel(Skill.HUNTER);

        switch (name) {
            case "Baby impling":
                return hunterLevel >= 17;
            case "Young impling":
                return hunterLevel >= 22;
            case "Gourmet impling":
                return hunterLevel >= 28;
            case "Earth impling":
                return hunterLevel >= 36;
            case "Essence impling":
                return hunterLevel >= 42;
            case "Eclectic impling":
                return hunterLevel >= 50;
            case "Nature impling":
                return hunterLevel >= 58;
            case "Magpie impling":
                return hunterLevel >= 65;
            case "Ninja impling":
                return hunterLevel >= 74;
            case "Dragon impling":
                return hunterLevel >= 83;
            default:
                return false;
        }
    }

    private void renderHighlight(Graphics2D graphics, NPC npc) {
        Shape bodyOutline = npc.getConvexHull();
        if (bodyOutline != null) {
            graphics.setColor(config.highlightBodyColor());
            graphics.setStroke(new BasicStroke(2));
            graphics.draw(bodyOutline);
        }
    }

    private void renderTileHighlight(Graphics2D graphics, NPC npc) {
        WorldPoint worldPoint = npc.getWorldLocation();
        if (worldPoint != null) {
            LocalPoint localPoint = LocalPoint.fromWorld(client, worldPoint);
            if (localPoint != null) {
                Polygon tilePolygon = Perspective.getCanvasTilePoly(client, localPoint);
                if (tilePolygon != null) {
                    Color tileColor = config.highlightTileColor();
                    Integer opacity = config.tileHighlightOpacity();


                    Color translucentTileColor = new Color(
                            tileColor.getRed(),
                            tileColor.getGreen(),
                            tileColor.getBlue(),
                            Math.min(255, Math.max(0, opacity))
                    );

                    graphics.setColor(translucentTileColor);
                    graphics.fillPolygon(tilePolygon);

                    graphics.setColor(config.highlightTileBorderColor());
                    graphics.drawPolygon(tilePolygon);
                }
            }
        }
    }
}
